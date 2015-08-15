import os, re

def SysInfo():
    values  = {}
    cache   = os.popen2("SYSTEMINFO")
    source  = cache[1].read()
    sysOpts = ["Host Name", "OS Name", "OS Version", "Product ID", "System Manufacturer", "System Model", "System type", "BIOS Version", "Domain", "Windows Directory", "Total Physical Memory", "Available Physical Memory", "Logon Server"]

    for opt in sysOpts:
        values[opt] = [item.strip() for item in re.findall("%s:\w*(.*?)\n" % (opt), source, re.IGNORECASE)][0]
    return values
	
SysInfo()