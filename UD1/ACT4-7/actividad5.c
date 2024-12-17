#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sched.h>
#include <time.h>

int main() {

     int n;
     pid_t  pid;
    printf("Ingrese el número de hijos para crear: ");
    scanf("%d", &n); 
    
    for (int i = 1; i <= n; i++) {
        pid = fork ();
    
        if (pid < 0) {
            printf("No se ha podido crear el proceso hijo...\n");
            exit (-1);
        } 

        if (pid == 0) {
          printf("Soy el hijo %d, Mi padre es %d y mi PID es %d\n", i, getppid(), getpid());
        }   
         else {  // El proceso padre
            wait(NULL);  // El padre espera a que termine su hijo
            break;  // El padre sale del ciclo para no crear más hijos
        }
    }
    return 0;
}
