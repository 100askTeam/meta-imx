FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

DEPENDS_mx6 += "imx-gst1.0-plugin"
DEPENDS_mx6ul += "imx-gst1.0-plugin"
DEPENDS_mx7 += "imx-gst1.0-plugin"

GST_CFLAGS_EXTRA = "${@base_contains('DISTRO_FEATURES', 'x11', '', \
                       base_contains('DISTRO_FEATURES', 'wayland', '-DEGL_API_FB -DWL_EGL_PLATFORM', '-DEGL_API_FB', d),d)}"
CFLAGS_append_mx6q = " ${GST_CFLAGS_EXTRA}"
CFLAGS_append_mx6dl = " ${GST_CFLAGS_EXTRA}"
CFLAGS_append_mx6sx = " ${GST_CFLAGS_EXTRA}"

PACKAGECONFIG_GL_mx6q = "${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'gles2', '', d)}"
PACKAGECONFIG_GL_mx6dl = "${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'gles2', '', d)}"
PACKAGECONFIG_GL_mx6sx = "${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'gles2', '', d)}"
PACKAGECONFIG_GL_mx6sl = "${@bb.utils.contains('DISTRO_FEATURES', 'opengl', \
                           base_contains('DISTRO_FEATURES', 'x11', \
                                    'opengl', '', d), '', d)}"

#PACKAGECONFIG_append_mx6q = " opencv"
#PACKAGECONFIG_append_mx6qp = " opencv"

#revert poky fido commit:cdc2c8aeaa96b07dfc431a4cf0bf51ef7f8802a3: move EGL to Wayland
PACKAGECONFIG[gles2]   = "--enable-gles2 --enable-egl,--disable-gles2 --disable-egl,virtual/libgles2 virtual/egl"
PACKAGECONFIG[wayland] = "--enable-wayland --disable-x11,--disable-wayland,wayland"

#i.MX specific
SRC_URI_append = " file://0004-modifiy-the-videoparse-rank.patch \
                   file://0007-camerabin-Add-one-property-to-set-sink-element-for-v.patch \
"
#common
SRC_URI_append += " file://0001-mpegtsmux-Need-get-pid-when-create-streams.patch \
                    file://0002-mpeg4videoparse-Need-detect-picture-coding-type-when.patch \
                    file://0003-mpegvideoparse-Need-detect-picture-coding-type-when-.patch \
                    file://0005-glfilter-Lost-frame-rate-info-when-fixate-caps.patch \
                    file://0006-opencv-Add-video-stitching-support-based-on-Open-CV.patch \
                    file://0001-gstreamer-gl.pc.in-don-t-append-GL_CFLAGS-to-CFLAGS.patch \
"

# i.MX6 patches for GST1.6
GPU_PATCHES = " file://0008-Fix-for-gl-plugin-not-built-in-wayland-backend.patch \
                file://0009-glplugin-Support-fb-backend-for-gl-plugins.patch \
                file://0010-glplugin-Change-wayland-default-res-to-1024x768.patch \
                file://0011-glplugin-gl-wayland-fix-loop-test-hang-in-glimagesin.patch \
                file://0012-glplugin-Fix-glimagesink-wayland-resize-showed-blurr.patch \
                file://0013-Add-directviv-to-glimagesink-to-improve-playback-per.patch \
                file://0014-MMFMWK-6930-glplugin-Accelerate-gldownload-with-dire.patch \
                file://0015-support-video-crop-for-glimagesink.patch \
                file://0016-Add-fps-print-in-glimagesink.patch \
                file://0017-glcolorconvert-convert-YUV-to-RGB-use-directviv.patch \
                file://0018-glwindow-work-around-for-no-frame-when-imxplayer-use.patch \
"

SRC_URI_append_mx6q  = "${GPU_PATCHES}"
SRC_URI_append_mx6dl = "${GPU_PATCHES}"
SRC_URI_append_mx6sx = "${GPU_PATCHES}"

# include fragment shaders
FILES_${PN}-opengl += "/usr/share/*.fs"

PACKAGE_ARCH_mx6 = "${MACHINE_SOCARCH}"
PACKAGE_ARCH_mx7 = "${MACHINE_SOCARCH}"
PACKAGE_ARCH_mx6ul = "${MACHINE_SOCARCH}"
