#Create directory to store the output

$TARGET = "C:\Users\$env:USERNAME\Documents\DDCN-Wi-Fi-Testing"

if(!(Test-Path -Path $TARGET)){
	New-Item -ItemType directory -Path $TARGET
}

#Assign date and time to variable for file names

$thedate = Get-Date -Uformat "%d-%m-%y-%H.%M.%S"

#Acquire DDCN network information and print to text file

netsh wlan show networks mode=bssid | Select-String "BPV_GUEST" -con 1,57 >> C:\Users\$env:USERNAME\Documents\DDCN-Wi-Fi-Testing\wifi-test-$thedate.txt
netsh wlan show networks mode=bssid | Select-String "BPV-Digital" -con 1,51 >> C:\Users\$env:USERNAME\Documents\DDCN-Wi-Fi-Testing\wifi-test-$thedate.txt
netsh interface ip show ipstats >> C:\Users\$env:USERNAME\Documents\DDCN-Wi-Fi-Testing\wifi-test-$thedate.txt
netsh interface ip show tcpstats >> C:\Users\$env:USERNAME\Documents\DDCN-Wi-Fi-Testing\wifi-test-$thedate.txt
timeout /t -1