FILESEXTRAPATHS_prepend_mx6 := "${THISDIR}/${PN}:"

MESA-DEMO-PATCH += " file://0001-Additional-eglSwapBuffer-calling-makes-wrong-throttl.patch \
                     file://0001-Add-OpenVG-demos-to-support-wayland.patch"

DEPENDS = "mesa"

REMOVE_GLU = "${@base_contains('DISTRO_FEATURES', 'wayland', \
                       base_contains('DISTRO_FEATURES', 'x11','glu x11', 'glu x11', d), '', d)}"

PACKAGECONFIG_remove_mx6q = "${REMOVE_GLU}"
PACKAGECONFIG_remove_mx6dl = "${REMOVE_GLU}"
PACKAGECONFIG_remove_mx6sx = "${REMOVE_GLU}"
PACKAGECONFIG_remove_mx6sl = "${REMOVE_GLU}"

PACKAGECONFIG[vg] = "--enable-vg,--disable-vg,virtual/libopenvg"

PACKAGECONFIG_append = "${@base_contains('DISTRO_FEATURES', 'wayland', \
                    base_contains('DISTRO_FEATURES', 'x11',' wayland vg', ' wayland vg', d), '', d)}"
