SOC_TOOLS_GPU_remove_imxgpu2d = "imx-gpu-viv-g2d"
SOC_TOOLS_GPU_remove_imxgpu3d = "imx-gpu-viv-tools-apitrace"

SOC_TOOLS_GPU_append_imxgpu   = " imx-gpu-sdk"
SOC_TOOLS_GPU_append_imxgpu3d = " imx-gpu-apitrace imx-gpu-apitrace-bin"

#Remove apitrace temporarily for 8mq and 8mn until the build break is fixed
SOC_TOOLS_GPU_remove_mx8mq = "imx-gpu-apitrace imx-gpu-apitrace-bin"
SOC_TOOLS_GPU_remove_mx8mn = "imx-gpu-apitrace imx-gpu-apitrace-bin"

SOC_TOOLS_GPU_append_imxdrm = " libdrm-tests"
