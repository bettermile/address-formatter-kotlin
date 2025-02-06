import com.bettermile.addressformatter.template.AddressTemplate
import kotlin.OptIn
import kotlin.String
import kotlin.collections.Map

@OptIn(com.bettermile.addressformatter.template.InternalForInheritanceAddressFormatterApi::class)
internal object AddressTemplates {
  public val test: AddressTemplate
    get() = object : AddressTemplate {
      override fun render(context: Map<String, String>): String = buildString {
        sequence<String> {
          yield(
            buildString {
              append(' ')
              context["content1"]?.also(::append)
              append(' ')
            }
          )
          yield(
            buildString {
              append(' ')
              context["content2"]?.also(::append)
              append(' ')
            }
          )
        }
        .firstOrNull(String::isNotBlank)?.also(::append)
      }
    }
}
