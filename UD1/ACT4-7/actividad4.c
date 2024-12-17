#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sched.h>
#include <time.h>

int main() {
    pid_t pid;

    for (int i = 1; i <= 3; i++) {
        pid = fork();

        if (pid < 0) {
            // Error al crear el hijo
            perror("Error al crear el proceso hijo");
            exit(1);
        } else if (pid == 0) {
            // Estamos en el proceso hijo
            printf("Soy el hijo %d, Mi padre es %d y mi PID es %d\n", i, getppid(), getpid());
            exit(0);  // Terminamos el proceso hijo
        }
        // El proceso padre sigue ejecutando el ciclo para crear más hijos
    }

    // Estamos en el proceso padre
    // Esperamos un momento para asegurar que los hijos terminen antes de que el padre imprima su mensaje
    sleep(1);
    printf("Proceso padre %d\n", getpid());

    return 0;
}
