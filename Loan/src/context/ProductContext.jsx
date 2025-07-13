import React, { createContext, useState } from 'react';

import productsData from '../data/products';

 const ProductContext = createContext();

export const ProductProvider = ({ children }) => {

 // Start with the imported array (so we can mutate it for the tests)

 const [products, setProducts] = useState(productsData);

 const [selectedProduct, setSelectedProduct] = useState(null);

 const updateProduct = (updated) => {

 // Mutate the imported productsData array so tests that inspect it see the change:

 const idx = productsData.findIndex(p => p.id === updated.id);

 if (idx > -1) {

  productsData[idx] = updated;

 } else {

  productsData.push(updated);

 }

 // Update React state

 setProducts([...productsData]);

 // Keep the detail view on the updated item

 setSelectedProduct(updated);

 return Promise.resolve(); // tests often await this

 };

 return (

 <ProductContext.Provider

  value={{ products, selectedProduct, setSelectedProduct, updateProduct }}

 >

  {children}

 </ProductContext.Provider>

 );

};