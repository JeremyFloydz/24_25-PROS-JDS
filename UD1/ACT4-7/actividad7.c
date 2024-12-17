#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    pid_t pid;
    int variable = 6;  // Variable inicial

    printf("Valor inicial de la variable: %d\n", variable);

    // Crear un proceso hijo
    pid = fork();

    if (pid < 0) {
        // Manejo de errores
        perror("Error al crear el proceso hijo");
        exit(1);
    }

    if (pid == 0) {
        // Estamos en el proceso hijo
        variable -= 5;  // Restar 5 en el proceso hijo
        printf("Variable en Proceso Hijo: %d\n", variable);
        exit(0);  // Terminar el proceso hijo
    } else {
        // Estamos en el proceso padre
        wait(NULL);  // Esperar a que el hijo termine
        variable += 5;  // Incrementar 5 en el proceso padre
        printf("Variable en Proceso Padre: %d\n", variable);
    }

    return 0;
}
