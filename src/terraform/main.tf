# Raw, unedited, & currently unworkable: you're still the pilot, time to update the flight plan!
provider "azurerm" {
  features {}
}

resource "azurerm_resource_group" "example" {
  name     = "example-resource-group"
  location = "eastus"
}

resource "azurerm_spring_cloud_service" "example" {
  name                = "example-spring-cloud-service"
  location            = azurerm_resource_group.example.location
  resource_group_name = azurerm_resource_group.example.name

  sku {
    name = "standard"
    tier = "Standard"
  }

  app_settings = {
    "SPRING_PROFILES_ACTIVE" = "prod"
    "SPRING_DATASOURCE_URL" = "jdbc:mysql://example.mysql.database.azure.com:3306/example?useSSL=true&requireSSL=false"
    "SPRING_DATASOURCE_USERNAME" = "example@example"
    "SPRING_DATASOURCE_PASSWORD" = "example"
  }

  depends_on = [
    azurerm_resource_group.example
  ]
}

resource "azurerm_spring_cloud_deployment" "example" {
  name                = "example-spring-cloud-deployment"
  resource_group_name = azurerm_resource_group.example.name
  spring_cloud_service_name = azurerm_spring_cloud_service.example.name

  deployment_settings {
    cpu = 1
    memory_in_gb = 1
    instance_count = 1
  }

  artifact {
    type = "maven"
    uri = "https://github.com/example/example/archive/master.zip"
    version = "1.0.0"
    artifact_id = "example"
    group_id = "com.example"
  }

  depends_on = [
    azurerm_spring_cloud_service.example
  ]
}