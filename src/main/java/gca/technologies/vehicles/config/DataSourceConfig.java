package gca.technologies.vehicles.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Clase encargada de gestionar la configuracion para la conexion
 * de bases de datos dentro del contexto de Spring para DBGCA
 */
@Configuration
@Slf4j
public class DataSourceConfig {

    /**
     * Hace referencia a la url de conexion a la BD
     */
    @Value("${datasource.url}")
    private String url;

    /**
     * Hace referencia al usuario de la BD
     */
    @Value("${datasource.user}")
    private String username;

    /**
     * Hace referencia a la contraseña de la BD
     */
    @Value("${datasource.password}")
    private String pw;

    /**
     * Hace referencia al driver gestor de la BD
     */
    @Value("${datasource.drive}")
    private String drive;

    /**
     * Hace referencia al metodo en el que se realiza la configuracion
     */
    private static final String DATABASE_CONFIGURATION = "dataSource";

    /**
     * Bean encargado de configurar los detalles para la conexion
     * de DBGCA
     * @return retorna la configuracion de la base de datos
     */
    @Bean(name = DATABASE_CONFIGURATION)
    public DataSource dataSource() {
        log.info(DATABASE_CONFIGURATION + " Ejecutando la configuracion de la BD en >>>>>>>> {}", this.getClass().getCanonicalName());
        return DataSourceBuilder.create()
                .driverClassName(drive)
                .url(url)
                .username(username)
                .password(pw)
                .build();
    }

}
