package org.grfstuff.compiler;


import org.graalvm.compiler.hotspot.HotSpotBackendFactory;
import org.graalvm.compiler.serviceprovider.ServiceProvider;
import org.graalvm.compiler.hotspot.amd64.AMD64HotSpotBackendFactory;

@ServiceProvider(HotSpotBackendFactory.class)
public class GrfStuffHotSpotBackendFactory extends AMD64HotSpotBackendFactory {
    public GrfStuffHotSpotBackendFactory(){
        System.out.println("GrfStuffHotSpotFactory instantiated");
    }
    public String toString() {
        return "GRFSTUFF_AMD64";
    }
}
