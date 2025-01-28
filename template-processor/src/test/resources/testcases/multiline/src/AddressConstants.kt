import com.bettermile.addressformatter.template.AddressTemplateDefinition

class AddressConstants {
    @AddressTemplateDefinition("""
        {{{attention}}}
        {{{house}}}
        {{#first}} {{{road}}} || {{{place}}} || {{{hamlet}}} {{/first}} {{{house_number}}}
        {{{postcode}}} {{#first}} {{{postal_city}}} || {{{town}}} || {{{city}}} || {{{village}}} || {{{municipality}}} || {{{hamlet}}} || {{{county}}} || {{{state}}} {{/first}}
        {{{archipelago}}}
        {{{country}}}
        """)
    val test = AddressTemplates.test
}
