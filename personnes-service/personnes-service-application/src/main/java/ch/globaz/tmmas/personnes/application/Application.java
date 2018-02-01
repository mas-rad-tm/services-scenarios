package ch.globaz.tmmas.personnes.application;

import ch.globaz.tmmas.personnes.application.configuration.DefaultProfileUtil;
import ch.globaz.tmmas.personnes.application.configuration.Profiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;

/**
 * Classe principale de l'application en mode jar.
 * Le serveur est embarqué par l'application
 * <b>Enable AutoConfiguration Exclusion:</b>HibernateJpaAutoConfiguration.class
 *
 */
@Configuration
@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan(basePackages = "ch.globaz.tmmas.personnes")
public class Application {



	private final Environment env;
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public Application (Environment env) {
		this.env = env;
	}

	/**
	 * Méthode exécutable démarrant l'application en mode jar
	 * @param args les arguments d'entrées
	 */
	public static void main(String []args)  {

		SpringApplication app = new SpringApplication(Application.class);
		DefaultProfileUtil.setDevelopmentProfile(app);

		Environment env = app.run(args).getEnvironment();

		logInitApplicationContext(env);

	}



	@PostConstruct
	public void initApplication() {

		ResourceProperties prop = new ResourceProperties();
		String[] staticLocation = prop.getStaticLocations();

		LOGGER.info("***********************************************************************");
		LOGGER.info("                     *** Static location paths ***                     ");
		LOGGER.info("***********************************************************************");

		LOGGER.info("Profile(s) : {} ",env.getActiveProfiles());

		Arrays.asList(staticLocation).forEach(location -> {
			LOGGER.info(location);
		});

		LOGGER.info("***********************************************************************");

		checkProfilesIntegrity();

	}


	private void checkProfilesIntegrity () {
		Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
		if (activeProfiles.contains(Profiles.DEV.value()) && activeProfiles.contains(
				Profiles.PRODUCTION.value())) {
			LOGGER.error("You have misconfigured your application! It should not run " +
					"with both the 'dev' and 'prod' profiles at the same time.");
		}
	}

	private static void logInitApplicationContext (Environment env) {

		String externalAdress;

		try {
			externalAdress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			externalAdress = "Undefined";
		}


		LOGGER.info("***********************************************************************");
		LOGGER.info("            *** Application context configuration ***                   ");
		LOGGER.info("***********************************************************************");

		LOGGER.info("Application '{}' is running!",env.getProperty("spring.application.name"));
		LOGGER.info("Local      : localhost:{}{}",env.getProperty("server.port"), env
				.getProperty
				("server" +
				".contextPath"));
		LOGGER.info("External   : {}:{}{}",externalAdress, env.getProperty("server.port"),
				env.getProperty("server.contextPath"));
		LOGGER.info("Profile(s) : {} ",env.getActiveProfiles());
		LOGGER.info("HTTP Proxy : {}:{} ",System.getProperties().getProperty("http.proxyHost"),
				System.getProperties().getProperty("http.proxyPort"));
		LOGGER.info("HTTPS Proxy: {}:{} ",System.getProperties().getProperty("https.proxyHost"),
				System.getProperties().getProperty("https.proxyPort"));
		LOGGER.info("***********************************************************************");

	}
}
