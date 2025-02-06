import com.bettermile.addressformatter.template.AddressTemplate
import kotlin.OptIn
import kotlin.String
import kotlin.collections.Map

@OptIn(com.bettermile.addressformatter.template.InternalForInheritanceAddressFormatterApi::class)
internal object AddressTemplates {
  public val test: AddressTemplate
    get() = object : AddressTemplate {
      override fun render(context: Map<String, String>): String = buildString {
        context["attention"]?.also(::append)
        append('\n')
        context["house"]?.also(::append)
        append('\n')
        sequence<String> {
          yield(
            buildString {
              append(' ')
              context["road"]?.also(::append)
              append(' ')
            }
          )
          yield(
            buildString {
              append(' ')
              context["place"]?.also(::append)
              append(' ')
            }
          )
          yield(
            buildString {
              append(' ')
              context["hamlet"]?.also(::append)
              append(' ')
            }
          )
        }
        .firstOrNull(String::isNotBlank)?.also(::append)
        append(' ')
        context["house_number"]?.also(::append)
        append('\n')
        context["postcode"]?.also(::append)
        append(' ')
        sequence<String> {
          yield(
            buildString {
              append(' ')
              context["postal_city"]?.also(::append)
              append(' ')
            }
          )
          yield(
            buildString {
              append(' ')
              context["town"]?.also(::append)
              append(' ')
            }
          )
          yield(
            buildString {
              append(' ')
              context["city"]?.also(::append)
              append(' ')
            }
          )
          yield(
            buildString {
              append(' ')
              context["village"]?.also(::append)
              append(' ')
            }
          )
          yield(
            buildString {
              append(' ')
              context["municipality"]?.also(::append)
              append(' ')
            }
          )
          yield(
            buildString {
              append(' ')
              context["hamlet"]?.also(::append)
              append(' ')
            }
          )
          yield(
            buildString {
              append(' ')
              context["county"]?.also(::append)
              append(' ')
            }
          )
          yield(
            buildString {
              append(' ')
              context["state"]?.also(::append)
              append(' ')
            }
          )
        }
        .firstOrNull(String::isNotBlank)?.also(::append)
        append('\n')
        context["archipelago"]?.also(::append)
        append('\n')
        context["country"]?.also(::append)
      }
    }
}
