
import React, { createContext, useState } from 'react';
import productsData from '../data/products';

export const ProductContext = createContext();

export const ProductProvider = ({ children }) => {
    const [products, setProducts] = useState(productsData);
    const [selectedProduct, setSelectedProduct] = useState(null);

    const updateProduct = (updated) => {
        const idx = productsData.findIndex(p => p.id === updated.id);
        if (idx > -1) {
            productsData[idx] = updated;
        } else {
            productsData.push(updated);
        }
        setProducts([...productsData]);
        setSelectedProduct(updated);
        return Promise.resolve();
    };

    return (
        <ProductContext.Provider value={{ products, selectedProduct, setSelectedProduct, updateProduct }}>
            {children}
        </ProductContext.Provider>
    );
};
