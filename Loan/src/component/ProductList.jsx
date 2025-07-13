import React, { useContext, useMemo } from "react";

import { ProductContext } from "../contexts/ProductContext";

import { FilterContext } from "../contexts/FilterContext";

import LowStockAlert from "./LowStockAlert";

import ProductFilter from "./ProductFilter";

import '../App.css';

const ProductList = () => {

 const { products, setSelectedProduct } = useContext(ProductContext);

 const { filter } = useContext(FilterContext);

 const filteredProducts = useMemo(() => {

 return filter === 'All'

  ? products

  : products.filter(p => p.category === filter);

 }, [products, filter]);

 return (

 <div className="product-list-container">

  <h2>Product List</h2>

  <ProductFilter />

  <LowStockAlert />

  <table border="1" cellPadding="5" cellSpacing="0" style={{ width: '100%', marginTop: '10px' }}>

  <thead>

   <tr>

   <th>Name</th><th>Category</th><th>Price ($)</th><th>Quantity</th>

   </tr>

  </thead>

  <tbody>

   {filteredProducts.map(product => (

   <tr key={product.id}>

    <td>

    <button

     onClick={() => setSelectedProduct(product)}

    >

     {product.name}

    </button>

    </td>

    <td>{product.category}</td>

    <td>{product.price.toFixed(2)}</td>

    <td>{product.quantity}</td>

   </tr>

   ))}

   {filteredProducts.length === 0 && (

   <tr>

    <td colSpan="4" style={{ textAlign: 'center' }}>No products found.</td>

   </tr>

   )}

  </tbody>

  </table>

 </div>

 );

};

export default ProductList;