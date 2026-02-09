package com.viajescarolina.destinations.app;

import jakarta.enterprise.context.ApplicationScoped;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

/**
 * Servicio para sanitizar contenido HTML del editor WYSIWYG.
 * Previene XSS permitiendo solo tags HTML seguros.
 */
@ApplicationScoped
public class HtmlSanitizerService {

    private final PolicyFactory policy;

    public HtmlSanitizerService() {
        // Política permisiva para contenido de blog
        this.policy = new HtmlPolicyBuilder()
                // Estructura básica
                .allowElements(
                        "p", "div", "span", "br", "hr",
                        "h1", "h2", "h3", "h4", "h5", "h6"
                )
                // Formato de texto
                .allowElements(
                        "strong", "b", "em", "i", "u", "s", "strike",
                        "sub", "sup", "mark", "small", "code", "pre"
                )
                // Listas
                .allowElements("ul", "ol", "li")
                // Enlaces
                .allowElements("a")
                .allowUrlProtocols("http", "https", "mailto")
                .allowAttributes("href", "target", "rel", "title").onElements("a")
                .requireRelNofollowOnLinks()
                // Imágenes
                .allowElements("img")
                .allowUrlProtocols("http", "https", "data")
                .allowAttributes("src", "alt", "title", "width", "height", "loading")
                .onElements("img")
                // Tablas
                .allowElements("table", "thead", "tbody", "tfoot", "tr", "th", "td", "caption")
                .allowAttributes("colspan", "rowspan").onElements("th", "td")
                // Citas y bloques
                .allowElements("blockquote", "cite", "q")
                // Multimedia embebida (YouTube, Vimeo)
                .allowElements("iframe")
                .allowUrlProtocols("https")
                .allowAttributes("src", "width", "height", "frameborder", "allowfullscreen", "allow")
                .onElements("iframe")
                // Figuras
                .allowElements("figure", "figcaption")
                // Atributos generales de estilo
                .allowAttributes("class", "id", "style").globally()
                // Atributos de datos para editores
                .allowAttributes("data-align", "data-size").globally()
                .toFactory();
    }

    /**
     * Sanitiza contenido HTML eliminando scripts y elementos peligrosos.
     *
     * @param unsafeHtml HTML sin sanitizar del editor
     * @return HTML seguro para almacenar y mostrar
     */
    public String sanitize(String unsafeHtml) {
        if (unsafeHtml == null || unsafeHtml.isBlank()) {
            return "";
        }
        return policy.sanitize(unsafeHtml);
    }

    /**
     * Extrae texto plano del HTML (útil para excerpts y búsqueda).
     *
     * @param html Contenido HTML
     * @return Texto sin tags HTML
     */
    public String extractPlainText(String html) {
        if (html == null || html.isBlank()) {
            return "";
        }
        // Eliminar todos los tags HTML
        return html.replaceAll("<[^>]*>", " ")
                   .replaceAll("\\s+", " ")
                   .trim();
    }

    /**
     * Calcula tiempo estimado de lectura basado en el contenido.
     *
     * @param html Contenido HTML
     * @return Minutos estimados de lectura
     */
    public int calculateReadingTime(String html) {
        String plainText = extractPlainText(html);
        if (plainText.isEmpty()) {
            return 1;
        }
        // Promedio 200 palabras por minuto
        int wordCount = plainText.split("\\s+").length;
        int minutes = (int) Math.ceil(wordCount / 200.0);
        return Math.max(1, minutes);
    }

    /**
     * Genera un excerpt del contenido HTML.
     *
     * @param html Contenido HTML
     * @param maxLength Longitud máxima del excerpt
     * @return Texto truncado con "..."
     */
    public String generateExcerpt(String html, int maxLength) {
        String plainText = extractPlainText(html);
        if (plainText.length() <= maxLength) {
            return plainText;
        }
        // Cortar en el último espacio antes del límite
        int cutPoint = plainText.lastIndexOf(' ', maxLength);
        if (cutPoint <= 0) {
            cutPoint = maxLength;
        }
        return plainText.substring(0, cutPoint) + "...";
    }
}
