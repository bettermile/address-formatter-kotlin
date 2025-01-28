import com.bettermile.addressformatter.template.AddressTemplate
import kotlin.OptIn
import kotlin.String
import kotlin.collections.Map

@OptIn(com.bettermile.addressformatter.template.InternalForInheritanceAddressFormatterApi::class)
internal object AddressTemplates {
  public val test: AddressTemplate
    get() = object : AddressTemplate {
      override fun render(context: Map<String, String>): String = buildString {
        sequence {
          yield(""" ${context["content1"] ?: ""} """)
          yield(""" ${context["content2"] ?: ""} """)
        }
        .firstOrNull(String::isNotBlank)?.also(::append)
      }
    }
}
