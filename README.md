# OpenBMC

[![Build Status](https://jenkins.openbmc.org/buildStatus/icon?job=latest-master)](https://jenkins.openbmc.org/job/latest-master/)

OpenBMC is a Linux distribution for management controllers used in devices such
as servers, top of rack switches or RAID appliances. It uses
[Yocto](https://www.yoctoproject.org/),
[OpenEmbedded](https://www.openembedded.org/wiki/Main_Page),
[systemd](https://www.freedesktop.org/wiki/Software/systemd/), and
[D-Bus](https://www.freedesktop.org/wiki/Software/dbus/) to allow easy
customization for your platform.

## Setting up your OpenBMC project

### 1) Prerequisite

See the
[Yocto documentation](https://docs.yoctoproject.org/ref-manual/system-requirements.html#required-packages-for-the-build-host)
for the latest requirements

#### Ubuntu

```sh
sudo apt install git python3-distutils gcc g++ make file wget \
    gawk diffstat bzip2 cpio chrpath zstd lz4 bzip2
```

#### Fedora

```sh
sudo dnf install git python3 gcc g++ gawk which bzip2 chrpath cpio \
    hostname file diffutils diffstat lz4 wget zstd rpcgen patch
```

### 2) Download the source

```sh
git clone https://github.com/openbmc/openbmc
cd openbmc
```

### 3) Target your hardware

Any build requires an environment set up according to your hardware target.
There is a special script in the root of this repository that can be used to
configure the environment as needed. The script is called `setup` and takes the
name of your hardware target as an argument.

The script needs to be sourced while in the top directory of the OpenBMC
repository clone, and, if run without arguments, will display the list of
supported hardware targets, see the following example:

```text
$ . setup <machine> [build_dir]
Target machine must be specified. Use one of:

bletchley               mori                    s8036
dl360poc                mtjade                  swift
e3c246d4i               mtmitchell              tatlin-archive-x86
ethanolx                nicole                  tiogapass
evb-ast2500             olympus-nuvoton         transformers
evb-ast2600             on5263m5                vegman-n110
evb-npcm750             p10bmc                  vegman-rx20
f0b                     palmetto                vegman-sx20
fp5280g2                qcom-dc-scm-v1          witherspoon
g220a                   quanta-q71l             witherspoon-tacoma
gbs                     romed8hm3               x11spi
greatlakes              romulus                 yosemitev2
gsj                     s2600wf                 zaius
kudo                    s6q
lannister               s7106
```

Once you know the target (e.g. evb-ast2600), source the `setup` script as follows:

```sh
. setup evb-ast2600
```

### 4) Build

```sh
bitbake obmc-phosphor-image
```

once this bash command is given the open bmc will start building it will take few hours to build,once done we can commence with the building of quemu.

## QEMU BUILD PROCESS

### 1)install pkg-config package
```sh
apt-get install -y pkg-config
```

### 2)install ninja-build package
```sh
apt install ninja-build
```

### 3)install libglib2.0-dev package
```sh
apt installlibglib2.0-dev
```

### 4)install canvas-npm package
```sh
apt install build-essential libcairo2-dev libpango1.0-dev libjpeg-dev libgif-dev librsvg2-dev

```

### 5)install canvas-npm package
```sh
apt-get install libvirt libvirt-bin bridge-utils uml-utilities qemu-system-common

```
once the necessary packages are installed now let us now clone from the github some basic modules provided as an open source
### 5) cloning from github
```sh
git clone https://github.com/openbmc/qemu.git

```
### 6) cloning from github
```sh
git clone https://github.com/openbmc/qemu.git

```
### 7) open qemu file
```sh
cd qemu
```
### 8) build qemu
```sh
git submodule update --init dtc
mkdir build
cd build
../configure --target-list=arm-softmmu
make

```
### 9) build process over

Built file will be located at: arm-softmmu/qemu-system-arm
refer: https://github.com/openbmc/docs/blob/master/cheatsheet.md

 ## os running command
 ### 1) windows
 ```sh
 cp /home/bmc.mtd .
./qemu-system-arm -m 1024 -machine ast2600-evb -nographic     -drive file=bmc.mtd,format=raw,if=mtd
```
### 2) ubuntu
```sh
scp ubuntu@10.0.4.98:/home/ubuntu/bmc.mtd .
```
## command to build and add new packages to open bmc firmware

### 1) to build kernel

 
```sh
bitbake virtual/kernel
```
### 2) to clean and build kernel
```sh
 bitbake -c clean virtual/kernel
bitbake -c cleanall virtual/kernel
bitbake virtual/kernel

```
### 3) to build a single package with bitbake

#### example: to build ptpd
```sh
bitbake ptpd
```

#### to clean ptptd
```sh
bitbake -c clean ptpd
bitbake -c cleanall ptpd
```
### 4) to clean and build open bmc image

```sh 
bitbake -c clean obmc-phosphor-image
bitbake -c cleanall obmc-phosphor-image 
bitbake obmc-phosphor-image
```

### 5) to add a new package
```sh
vi meta-evb/meta-evb-aspeed/meta-evb-ast2600/conf/layer.conf   

IMAGE_INSTALL:append = " ntp"

[add this chnages in github ] --> push it

 . setup ast1600-evb

 bitbake obmc-phosphor-image
 ```

 ### 6) to build u-boot
```sh
bitbake u-boot
```

### 7) upload the generated open bmc image in github


