# 指定基础镜像
FROM ubuntu:18.04

# 定义环境变量
ENV RISCV=/opt/riscv
ENV PATH=$RISCV/bin:$PATH
ENV MAKEFLAGS=-j4

# 指定RUN命令的执行目录
WORKDIR $RISCV

# 安装基本工具
RUN apt update && \
	apt install -y autoconf automake autotools-dev curl libmpc-dev libmpfr-dev libgmp-dev gawk build-essential bison flex texinfo gperf libtool patchutils bc zlib1g-dev libexpat-dev pkg-config git libusb-1.0-0-dev device-tree-compiler default-jdk gnupg vim 

# riscv-gnu-toolchain(向量扩展指令对应版本)的架构
RUN git clone -b rvv-0.9.x-for-book --single-branch https://github.com/chadyuu/riscv-gnu-toolchain.git
RUN	cd riscv-gnu-toolchain && git submodule update --init --recursive riscv-binutils
RUN	cd riscv-gnu-toolchain && git submodule update --init --recursive riscv-gcc
RUN	cd riscv-gnu-toolchain && git submodule update --init --recursive riscv-newlib
RUN	cd riscv-gnu-toolchain && git submodule update --init --recursive riscv-gdb
RUN cd riscv-gnu-toolchain && mkdir build && cd build && ../configure --prefix=${RISCV} --enable-multilib && make

# 下载riscv-tests
RUN git clone -b master --single-branch https://github.com/riscv/riscv-tests && \
	cd riscv-tests && git checkout c4217d88bce9f805a81f42e86ff56ed363931d69 && \
	git submodule update --init --recursive

# 安装sbt
RUN echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" | tee -a /etc/apt/sources.list.d/sbt.list && \
	echo "deb https://repo.scala-sbt.org/scalasbt/debian /" | tee /etc/apt/sources.list.d/sbt_old.list && \
	curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | apt-key add && \
	apt-get update && apt-get install -y sbt