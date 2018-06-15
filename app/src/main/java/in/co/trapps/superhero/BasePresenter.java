package in.co.trapps.superhero;

/**
 * @author Akash Patra
 */
public interface BasePresenter<T> {
    void bind(T view);

    void unbind();
}
