#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    pid_t pid;

    // Crear el primer hijo
    if ((pid = fork()) == 0) {
        // Hijo 1
        printf("Soy el hijo 1, Mi padre es %d y mi PID es %d\n", getppid(), getpid());
        exit(0);
    }

    // Crear el segundo hijo
    if ((pid = fork()) == 0) {
        // Hijo 2
        printf("Soy el hijo 2, Mi padre es %d y mi PID es %d\n", getppid(), getpid());

        // Crear el hijo 3 dentro del hijo 2
        if ((pid = fork()) == 0) {
            // Hijo 3
            printf("Soy el hijo 3, Mi padre es %d y mi PID es %d\n", getppid(), getpid());
            exit(0);
        }

        // Hijo 2 espera al hijo 3
        wait(NULL);
        exit(0);
    }

    // Proceso padre espera a los hijos
    wait(NULL); // Espera al hijo 1
    wait(NULL); // Espera al hijo 2

    return 0;
}
