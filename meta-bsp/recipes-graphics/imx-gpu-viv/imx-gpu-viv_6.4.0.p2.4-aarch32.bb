require imx-gpu-viv-v6.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=228c72f2a91452b8a03c4cab30f30ef9"

# FIXME: Remove this patch when upstream handles backend-specific libGL
SRC_URI += "file://Add-dummy-libgl.patch"

SRC_URI[md5sum] = "bbb977a4c9b30af22908dbd84c609b64"
SRC_URI[sha256sum] = "ef044bb135f71853956e318a32c4cfbc035ed378f9aecd4002abf53b03c6c222"

do_install_append() {
    if [ "${IS_MX6SL}" != "1" ]; then
        if [ "${USE_WL}" = "yes" -o "${USE_X11}" = "yes" ]; then
            cp ${S}/gpu-core/usr/lib/libGL-${backend}.so ${D}${libdir}/libGL.so.1.2.0
        fi
    fi
}

COMPATIBLE_MACHINE = "(mx6q|mx6dl|mx6sx|mx6sl|mx7ulp)"
