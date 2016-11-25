package com.azurion.launcher;

public class Machine {

	private enum OS{
		WINDOWS, LINUX, MAC, SOLARIS
	};
	
	private enum ARCH{
		AMD64, X86
	};
	
	private OS OsType = null;
	
	private ARCH ArchType = null;
	
	private void defineOS(){
		String opersys = System.getProperty("os.name").toLowerCase();
		
		if (opersys.contains("win")){
			this.OsType = OS.WINDOWS;
		}else if (opersys.contains("nix") || opersys.contains("nux") || opersys.contains("aix")){
			this.OsType = OS.LINUX;
		}else if (opersys.contains("mac")){
			this.OsType = OS.MAC;
		}else{
			this.OsType = OS.SOLARIS;
		}
	}
	
	private void defineArchitecture(){
		String arch = System.getenv("PROCESSOR_ARCHITECTURE");
		String wow64Arch = System.getenv("PROCESSOR_ARCHITEW6432");

		this.ArchType = arch.endsWith("64")
		                  || wow64Arch != null && wow64Arch.endsWith("64")
		                      ? ARCH.AMD64: ARCH.X86;
	}
	
	public Machine(){
		defineOS();
		defineArchitecture();
	}
	
	// CHECK OS
	public boolean isWindowsOS(){
		if (this.OsType == OS.WINDOWS)
			return true;
		return false;
	}
	
	public boolean isLinuxOS(){
		if (this.OsType == OS.LINUX)
			return true;
		return false;
	}
	
	public boolean isMacOS(){
		if (this.OsType == OS.MAC)
			return true;
		return false;
	}
	
	// CHECK ARCHITECTURE
	
	public boolean is64bArch(){
		if (this.ArchType == ARCH.AMD64)
			return true;
		return false;
	}
	
	public boolean is32bArch(){
		if (this.ArchType == ARCH.X86)
			return true;
		return false;
	}
	
	// General Checking Functions
	
	public ARCH getArchitecture(){
		return ArchType;
	}
	
	public OS getOS(){
		return OsType;
	}
}
