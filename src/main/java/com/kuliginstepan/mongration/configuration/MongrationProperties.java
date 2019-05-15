package com.kuliginstepan.mongration.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * Configuration properties used to configure {@link com.kuliginstepan.mongration.Mongration}
 */
@Data
@ConfigurationProperties(prefix = "mongration")
public class MongrationProperties {

    /**
     * Changelogs collection name
     */
    private String changelogsCollection = "mongration_changelogs";

    /**
     * Enable or disable mongration
     */
    private boolean enabled = true;
}
