OS=$(shell uname)

JAVA_HOME=/usr/lib/jvm/jdk-13

build_and_test: clean test

info:
	@echo OS=${OS}
	@echo LIBSUFFIX=${LIBSUFFIX}
	@echo JAVA_HOME=${JAVA_HOME}

clean:
	${RM} -rf build 

build/org.grfstuff.compiler:
	mkdir -p build/org.grfstuff.compiler
	${JAVA_HOME}/bin/javac -g \
      -d build \
      --add-exports jdk.internal.vm.compiler/org.graalvm.compiler.hotspot=org.grfstuff.compiler\
      --add-exports jdk.internal.vm.compiler/org.graalvm.compiler.serviceprovider=org.grfstuff.compiler\
      --add-exports jdk.internal.vm.compiler/org.graalvm.compiler.hotspot.amd64=org.grfstuff.compiler\
      --module-source-path src\
      --module org.grfstuff.compiler\
      $(shell find src/org.grfstuff.compiler -name *.java)


build/org.grfstuff.violajones: 
	mkdir -p build/org.grfstuff.violajones
	${JAVA_HOME}/bin/javac -g \
      -d build/org.grfstuff.violajones\
      --source-path src/org.grfstuff.violajones\
      $(shell find src/org.grfstuff.violajones -name *.java)

test: clean build/org.grfstuff.compiler build/org.grfstuff.violajones
	${JAVA_HOME}/bin/java \
      -XX:+UnlockExperimentalVMOptions -XX:+UseJVMCICompiler -XX:-TieredCompilation\
      -XX:CompileCommand=compileonly,org/grfstuff/violajones/ViolaJones.compute\
      -XX:CompileCommand=compileonly,org/grfstuff/violajones/ViolaJOnes.run\
		--add-modules org.grfstuff.compiler\
		--add-exports jdk.internal.vm.compiler/org.graalvm.compiler.hotspot=org.grfstuff.compiler\
      --add-exports jdk.internal.vm.compiler/org.graalvm.compiler.serviceprovider=org.grfstuff.compiler\
      --add-exports jdk.internal.vm.compiler/org.graalvm.compiler.hotspot.amd64=org.grfstuff.compiler\
      -p build -m org.grfstuff.compiler\
      -classpath build/org.grfstuff.violajones\
      org.grfstuff.violajones.ViolaJones
