java -XX:+HeapDumpOnOutOfMemoryError -mn256m -mx512m ConsumeHeap -XX:HeapDumpPath

java -XX:+UnlockDiagnosticVMOptions -XX:+PrintFlagsFinal -version

java -XX:+PrintCommandLineFlags -version

JDK_HOME/bin/jstat.exe -gc <PROCESS ID OF YOUR JAVA APPLICATION>

jstat.exe -gc 4400

S0C  Current survivor space 0 capacity (KB).
S1C  Current survivor space 1 capacity (KB).
S0U  Survivor space 0 utilization (KB).
S1U  Survivor space 1 utilization (KB).
EC  Current eden space capacity (KB).
EU  Eden space utilization (KB).
OC  Current old space capacity (KB).
OU  Old space utilization (KB).
PC  Current permanent space capacity (KB).
PU  Permanent space utilization (KB).
YGC  Number of young generation GC Events.
YGCT  Young generation garbage collection time.
FGC  Number of full GC events.
FGCT  Full garbage collection time.
GCT  Total garbage collection time.