import com.bettermile.addressformatter.template.AddressTemplate
import com.bettermile.addressformatter.template.AddressTemplateDefinition

class AddressConstants {
    @AddressTemplateDefinition("test1", propertyName = "test1")
    @AddressTemplateDefinition("test2", propertyName = "test2")
    val test: Map<String, AddressTemplate> = mapOf(
        "test1" to AddressTemplates.test1,
        "test2" to AddressTemplates.test2,
    )
}
