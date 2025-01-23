import com.bettermile.addressformatter.template.AddressTemplateDefinition

class AddressConstants {
    @AddressTemplateDefinition("{{# first }} {{{content1}}} || {{{content2}}} {{/ first }}")
    val test = AddressTemplates.test
}
