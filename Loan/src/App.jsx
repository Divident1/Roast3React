import React from 'react';
import { ProductProvider } from './context/ProductContext';
import { FilterProvider } from './context/FilterContext';

import ProductList from './component/ProductList';
import ProductDetail from './component/ProductDetail';

function App() {
  return (
    <ProductProvider>
      <FilterProvider>
        <div className="App">
          <h1>My Product App</h1>
          <ProductList />
          <ProductDetail />
        </div>
      </FilterProvider>
    </ProductProvider>
  );
}

export default App;
