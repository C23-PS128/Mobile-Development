package com.bangkit.capstone.beangreader.ui.screen.scan

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.FlashOff
import androidx.compose.material.icons.rounded.FlashOn
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import com.bangkit.capstone.beangreader.ui.screen.scan.component.CameraPreview
import com.bangkit.capstone.beangreader.ui.screen.scan.util.executor
import com.bangkit.capstone.beangreader.ui.screen.scan.util.getCameraProvider
import com.bangkit.capstone.beangreader.ui.screen.scan.util.takePicture
import com.bangkit.capstone.beangreader.ui.screen.scan.util.toFile
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun ScanScreen() {
    val context = LocalContext.current

    ScanContent(
        onImageFiled = { _,_ -> },
        onHelpClick = { /*TODO*/ },
        onNavigateUp = { /*TODO*/ }
    )
}

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ScanContent(
    onImageFiled: (File, isFromCamera: Boolean) -> Unit,
    onHelpClick: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val permissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                coroutineScope.launch {
                    onImageFiled(uri.toFile(context), false)
                }
            }
        }
    )
    LaunchedEffect(Unit) {
        permissionState.launchPermissionRequest()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Scan Coffee Beans") },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateUp
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onHelpClick) {
                        Icon(
                            imageVector = Icons.Outlined.HelpOutline,
                            contentDescription = "Help"
                        )
                    }
                }
            )
        }
    ) {
        Box(modifier = modifier.padding(it)) {
            PermissionRequired(
                permissionState = permissionState,
                permissionNotGrantedContent = {},
                permissionNotAvailableContent = {}
            ) {
                val lifecycleOwner = LocalLifecycleOwner.current
                var previewUseCase by remember { mutableStateOf<UseCase>(Preview.Builder().build()) }
                var flashModeOn by remember { mutableStateOf(false) }
                val imageCaptureUseCase by remember {
                    mutableStateOf(
                        ImageCapture.Builder()
                            .setCaptureMode(CAPTURE_MODE_MINIMIZE_LATENCY)
                            .build()
                    )
                }
                Box {
                    CameraPreview(
                        modifier = Modifier.fillMaxSize(),
                        onUseCase = { useCase ->
                            previewUseCase = useCase
                        }
                    )
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                try {
                                    onImageFiled(imageCaptureUseCase.takePicture(context.executor), true)
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(24.dp)
                            .size(64.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Circle,
                            tint = Color.White,
                            contentDescription = "Scan"
                        )
                    }
                    IconButton(
                        onClick = {
                            singlePhotoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color(0x51000000)
                        ),
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(24.dp)
                            .size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Image,
                            tint = Color.White,
                            contentDescription = "Pick Image from Gallery"
                        )
                    }
                    IconButton(
                        onClick = {
                            flashModeOn = !flashModeOn
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color(0x51000000)
                        ),
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(24.dp)
                            .size(48.dp)
                    ) {
                        Icon(
                            imageVector = if (flashModeOn)
                                Icons.Rounded.FlashOn else Icons.Rounded.FlashOff,
                            tint = Color.White,
                            contentDescription = "Turn on The Flash"
                        )
                    }
                }
                LaunchedEffect(previewUseCase, flashModeOn) {
                    val cameraProvider = context.getCameraProvider()
                    try {
                        cameraProvider.unbindAll()
                        val camera = cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            cameraSelector,
                            previewUseCase,
                            imageCaptureUseCase
                        )
                        if (camera.cameraInfo.hasFlashUnit()) {
                            camera.cameraControl.enableTorch(flashModeOn)
                        }
                    } catch (e: Exception) {
                        Log.e("Camera Content", "ScanContent: $e" )
                    }
                }
            }
        }
    }
}