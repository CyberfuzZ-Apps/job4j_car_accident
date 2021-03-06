package ru.job4j.accident;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import ru.job4j.accident.config.DataConfig;
import ru.job4j.accident.config.HbmConfig;
import ru.job4j.accident.config.JdbcConfig;
import ru.job4j.accident.config.WebConfig;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * Класс WebInit
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class WebInit implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(
                WebConfig.class,
                /* JdbcConfig.class,
                HbmConfig.class, */
                SecurityConfig.class,
                DataConfig.class
        );
        ac.refresh();

        /* Для правильного отображения кириллицы */
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        filter.setForceRequestEncoding(true);
        FilterRegistration.Dynamic encoding = servletContext.addFilter("encoding", filter);
        encoding.addMappingForUrlPatterns(null, false, "/*");

        DispatcherServlet servlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}
