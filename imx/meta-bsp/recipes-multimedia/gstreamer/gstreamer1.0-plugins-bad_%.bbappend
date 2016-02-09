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

PACKAGECONFIG_append_mx6q = " opencv"
PACKAGECONFIG_append_mx6qp = " opencv"

#revert poky fido commit:cdc2c8aeaa96b07dfc431a4cf0bf51ef7f8802a3: move EGL to Wayland
PACKAGECONFIG[gles2]   = "--enable-gles2 --enable-egl,--disable-gles2 --disable-egl,virtual/libgles2 virtual/egl"
PACKAGECONFIG[wayland] = "--enable-wayland --disable-x11,--disable-wayland,wayland"

# i.MX6 patches for GST1.6
GPU_PATCHES = " file://0008-Adding-some-fragment-shaders-for-glshader-plugin.patch \
                file://0010-Fix-for-gl-plugin-not-built-in-wayland-backend.patch \
                file://0011-glplugin-Add-directviv-to-glimagesink-to-improve-playback-performance.patch \
                file://0012-glplugin-Accelerate-gldownload-with-directviv-API.patch \
                file://0013-glplugin-support-video-crop-for-glimagesink.patch \
                file://0014-glplugin-Support-fb-backend-for-gl-plugins.patch \
                file://0015-glplugin-Change-wayland-default-res-to-1024x768.patch \
                file://0016-Add-one-deinterlacing-fragment-shader-file.patch \
                file://0017-glplugin-Add-fps-print-in-glimagesink.patch \
                file://0019-glplugin-gl-wayland-fix-loop-test-hang-in-glimagesin.patch \
                file://0020-glplugin-Fix-glimagesink-wayland-resize-showed-blurr.patch \
                file://0021-glplugin-fix-gleffects-fisheye-shader-co.patch \
"

SRC_URI_append_mx6q  = "${GPU_PATCHES}"
SRC_URI_append_mx6dl = "${GPU_PATCHES}"
SRC_URI_append_mx6sx = "${GPU_PATCHES}"

# include fragment shaders
FILES_${PN}-opengl += "/usr/share/*.fs"

PACKAGE_ARCH_mx6 = "${MACHINE_SOCARCH}"
PACKAGE_ARCH_mx7 = "${MACHINE_SOCARCH}"
PACKAGE_ARCH_mx6ul = "${MACHINE_SOCARCH}"
