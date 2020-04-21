
module org.grfstuff.compiler {
   // requires jdk.internal.vm.ci;
    requires  jdk.internal.vm.compiler;
   // exports org.grfstuff.compiler;
    //uses org.graalvm.compiler.hotspot.HotSpotBackendFactory;
    provides org.graalvm.compiler.hotspot.HotSpotBackendFactory with org.grfstuff.compiler.GrfStuffHotSpotBackendFactory;
}
