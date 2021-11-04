/**
 * When we define this here, we can now used type T without any special hash
 * function passing since we define here once
 * */
namespace std {
template <>
struct hash<T> {
    size_t operator()(const T& s) const;
} size_t hash<T>::operator()(const T& s) const {
    // implement your operator here
    return 1;
}
}  // namespace std
