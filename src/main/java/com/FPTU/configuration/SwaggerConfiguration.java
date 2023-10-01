package com.FPTU.configuration;


import static io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "swagger")
public class SwaggerConfiguration {

  private String appName;

  private String appDescription;

  private String appVersion;

  private String appLicense;

  private String appLicenseUrl;

  private String contactName;

  private String contactUrl;

  private String contactMail;


  @Bean
  public OpenAPI openApi() {

    final Info apiInformation = getApiInformation();
    final Components components = new Components();

    final String schemeName = "bearerAuth";
    components.addSecuritySchemes(schemeName, new SecurityScheme().name(schemeName)
        .type(HTTP).scheme("bearer").bearerFormat("JWT"));

    final OpenAPI openApi = new OpenAPI();
    openApi.setInfo(apiInformation);
    openApi.setComponents(components);
    openApi.addSecurityItem(new SecurityRequirement().addList(schemeName));

    return openApi;
  }

  private Info getApiInformation() {

    final License license = new License();
    license.setName(appLicense);
    license.setUrl(appLicenseUrl);

    final Contact contact = new Contact();
    contact.setName(contactName);
    contact.setUrl(contactUrl);
    contact.setEmail(contactMail);


    final Info info = new Info();
    info.setTitle(appName);
    info.setVersion(appVersion);
    info.setDescription(appDescription);
    info.setLicense(license);
    info.setContact(contact);

    return info;
  }

}
