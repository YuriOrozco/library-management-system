# library-management-system

## Descripción
El **Library management system** es una aplicaction Java diseñada para gestionar bibliotecas, permitiendo a los usuarios añadir sucursales, libros y usuarios, así como gestionar préstamos y devoluciones de libros. Este sistema también permite calcular las multas acumuladas por los usuarios por la devolución tardía de libros.

## Características
- **Gestión de Sucursales:** Añadir y listar sucursales.
- **Gestión de Libros:** Añadir y listar libros en diferentes sucursales.
- **Gestión de Usuarios:** Añadir y listar usuarios.
- **Préstamos de Libros:** Realizar préstamos de libros a usuarios.
- **Devoluciones de Libros:** Procesar devoluciones de libros y calcular multas.
- **Multas de Usuarios:** Calcular y visualizar las multas acumuladas por los usuarios.

  1. **Clonar el Repositorio:**
    ```bash
    git clone https://github.com/tuusuario/turepositorio.git
    cd turepositorio
    ```

2. **Compilar el Proyecto:**
    ```bash
    javac -d bin -sourcepath src src/com/mycompany/library/*.java
    ```

3. **Ejecutar la Aplicación:**
    ```bash
    java -cp bin com.mycompany.library.LibraryManagement
    ```

## Uso
1. **Añadir una Sucursal:**
    - Selecciona la opción 1 en el menú.
    - Introduce los detalles de la sucursal.
  
2. **Añadir un Libro:**
    - Selecciona la opción 2 en el menú.
    - Introduce los detalles del libro y selecciona la sucursal.

3. **Añadir un Usuario:**
    - Selecciona la opción 3 en el menú.
    - Introduce los detalles del usuario.

4. **Prestar un Libro:**
    - Selecciona la opción 4 en el menú.
    - Introduce los detalles del préstamo (ID del usuario, ID del libro, ID de la sucursal).

5. **Devolver un Libro:**
    - Selecciona la opción 5 en el menú.
    - Introduce los detalles de la devolución (ID del usuario, ID del libro, ID de la sucursal, fecha de devolución).

6. **Obtener Deudas:**
    - Selecciona la opción 6 en el menú.
    - Obtén las deudas acumuladas por todos los usuarios o por un usuario específico.

7. **Ver Préstamos:**
    - Selecciona la opción 7 en el menú.
    - Visualiza los préstamos realizados por todos los usuarios.
8. **Ver sucursales:**
   - Selecciona la opción 8  del menú.
   - Visualiza las sucursales existentes.
   
9. **Ver usuarios:**
    - Selecciona la opción 9 del menú.
    - Visualiza los usuarios existentes.
      
10. **Salir del sistema:**
    - Selecciona la opción 10 del menú
