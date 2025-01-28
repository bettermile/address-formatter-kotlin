import com.bettermile.addressformatter.template.AddressTemplate
import kotlin.OptIn
import kotlin.String
import kotlin.collections.Map

@OptIn(com.bettermile.addressformatter.template.InternalForInheritanceAddressFormatterApi::class)
internal object AddressTemplates {
  public val test: AddressTemplate
    get() = object : AddressTemplate {
      override fun render(context: Map<String, String>): String = buildString {
        append('\n')
        context["attention"]?.also(::append)
        append('\n')
        context["house"]?.also(::append)
        append('\n')
        sequence {
          yield(""" ${context["road"] ?: ""} """)
          yield(""" ${context["place"] ?: ""} """)
          yield(""" ${context["hamlet"] ?: ""} """)
        }
        .firstOrNull(String::isNotBlank)?.also(::append)
        append(' ')
        context["house_number"]?.also(::append)
        append('\n')
        context["postcode"]?.also(::append)
        append(' ')
        sequence {
          yield(""" ${context["postal_city"] ?: ""} """)
          yield(""" ${context["town"] ?: ""} """)
          yield(""" ${context["city"] ?: ""} """)
          yield(""" ${context["village"] ?: ""} """)
          yield(""" ${context["municipality"] ?: ""} """)
          yield(""" ${context["hamlet"] ?: ""} """)
          yield(""" ${context["county"] ?: ""} """)
          yield(""" ${context["state"] ?: ""} """)
        }
        .firstOrNull(String::isNotBlank)?.also(::append)
        append('\n')
        context["archipelago"]?.also(::append)
        append('\n')
        context["country"]?.also(::append)
        append('\n')
      }
    }
}
