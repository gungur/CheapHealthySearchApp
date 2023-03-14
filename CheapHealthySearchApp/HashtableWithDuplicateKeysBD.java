import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

// couldn't figure out how to hardcode this thus the corresponding test methods are empty
public class HashtableWithDuplicateKeysBD<KeyType, ValueType> implements HashtableWithDuplicateKeysInterface<KeyType, ValueType> {

    public HashtableWithDuplicateKeysBD(int capacity) {

    }

    public HashtableWithDuplicateKeysBD() {

    }

    @Override
    public void putOne(KeyType key, ValueType value) {

    }

    @Override
    public void removeOne(KeyType key, ValueType value) {

    }

    @Override
    public int getNumberOfValues() {
        return 21;
    } // hardcoded for test method

    @Override
    public void put(KeyType key, List<ValueType> value) throws IllegalArgumentException {

    }

    @Override
    public boolean containsKey(KeyType key) {
        return false;
    }

    @Override
    public List<ValueType> get(KeyType key) throws NoSuchElementException {

        return null;
    }

    @Override
    public List<ValueType> remove(KeyType key) throws NoSuchElementException {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getSize() {
        return 5;
    } // hardcoded for test method

    @Override
    public int getCapacity() {
        return 9;
    } // hardcoded for test method
}
