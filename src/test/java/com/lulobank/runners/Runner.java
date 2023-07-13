package com.lulobank.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {"pretty", "html:target/cucumber"},
        glue = "com/lulobank/stepdefinitions",
        tags = "@upload_image",
        snippets = CAMELCASE,
        monochrome = true
)
public class Runner {
}
