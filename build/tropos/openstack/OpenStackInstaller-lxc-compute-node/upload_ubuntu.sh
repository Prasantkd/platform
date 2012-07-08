#!/bin/bash

# Simple script to download Ubuntu Oneiric 11.10 from ubuntu.com
# and publish to cloud environment for use


TMPAREA=/tmp/__upload

# Process Command Line
while getopts a:p:t:C:x:y:z:w: opts
do
  case $opts in
    a)
        ADMIN=${OPTARG}
        ;;
    p)
        PASSWORD=${OPTARG}
        ;;
    t)
        TENANT=${OPTARG}
        ;;
    C)
        ENDPOINT=${OPTARG}
        ;;
    x)
        ARCH=${OPTARG}
        ;;
    y)
        DISTRO=${OPTARG}
        ;;
    z)
        CODENAME=${OPTARG}
        ;;
    w)
        VERSION=${OPTARG}
        ;;
    *)
        echo "Syntax: $(basename $0) -u USER -p KEYSTONE -t TENANT -C CONTROLLER_IP -x ARCH -y DISTRO -z CODENAME -w VERSION"
        exit 1
        ;;
  esac
done

TARBALL=${DISTRO}-${VERSION}-server-cloudimg-${ARCH}.tar.gz
TARBALL_PATH=/root/upload-${CODENAME}

# You must supply the API endpoint
if [[ ! $ENDPOINT ]]
then
        echo "Syntax: $(basename $0) -a admin -p PASSWORD -t TENANT -C CONTROLLER_IP"
        exit 1
fi



mkdir -p ${TMPAREA}
if [ ! -f ${TMPAREA}/${TARBALL} ]
then
	#wget -O ${TMPAREA}/${TARBALL} http://uec-images.ubuntu.com/releases/${CODENAME}/release/${TARBALL}
        cp -f ${TARBALL_PATH}/${TARBALL} ${TMPAREA}/${TARBALL}
fi

if [ -f ${TMPAREA}/${TARBALL} ]
then
	cd ${TMPAREA}
	tar zxf ${TARBALL}
	DISTRO_IMAGE=$(ls *-${ARCH}.img)
	DISTRO_KERNEL=$(ls *-${ARCH}-vmlinuz-virtual)

	KERNEL=$(glance -I ${ADMIN} -K ${PASSWORD} -T ${TENANT} -N http://${ENDPOINT}:5000/v2.0 add name="${DISTRO} ${VERSION} ${ARCH} Kernel" disk_format=aki container_format=aki distro="${DISTRO} ${VERSION}" is_public=true < ${DISTRO_KERNEL} | awk '/ ID/ { print $6 }')

	AMI=$(glance -I ${ADMIN} -K ${PASSWORD} -T ${TENANT} -N http://${ENDPOINT}:5000/v2.0 add name="${DISTRO} ${VERSION} ${ARCH} Server" disk_format=ami container_format=ami distro="${DISTRO} ${VERSION}" kernel_id=${KERNEL} is_public=true < ${DISTRO_IMAGE} | awk '/ ID/ { print $6 }')

	echo "${DISTRO} ${VERSION} ${ARCH} now available in Glance (${AMI})"

	rm -f /tmp/__upload/*{.img,-vmlinuz-virtual,loader,floppy}
else
	echo "Tarball not found!"
fi
