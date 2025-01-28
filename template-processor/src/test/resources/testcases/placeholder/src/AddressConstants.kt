import com.bettermile.addressformatter.template.AddressTemplateDefinition

class AddressConstants {
    @AddressTemplateDefinition("{{{ content }}}")
    val test = AddressTemplates.test
}
