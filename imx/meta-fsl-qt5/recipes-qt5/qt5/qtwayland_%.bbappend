FILESEXTRAPATHS_prepend := "${THISDIR}/qtwayland-git"

SRC_URI = "git://gitorious.org/qt/qtwayland.git;protocol=https;branch=5.4 \
           file://0001-xcomposite-glx-Fix-build-on-Qt-5.2.1.patch \
           file://0001-examples.pro-include-server-buffer-only-when-buildin.patch"
