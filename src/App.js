
import React from 'react';
import { ProductProvider } from './contexts/ProductContext';
import { FilterProvider } from './contexts/FilterContext';
import ProductList from './components/ProductList';

function App() {
    return (
        <ProductProvider>
            <FilterProvider>
                <ProductList />
            </FilterProvider>
        </ProductProvider>
    );
}

export default App;
