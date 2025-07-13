import React, { useContext, useState, useEffect } from 'react';

import { ProductContext } from '../contexts/ProductContext';

const ProductDetail = () => {

 const { selectedProduct, updateProduct } = useContext(ProductContext);

 const empty = { id: '', name: '', category: '', price: '', quantity: '' };

 const [edited, setEdited] = useState(empty);

 useEffect(() => {

 if (selectedProduct) {

  setEdited({

  id: selectedProduct.id,

  name: selectedProduct.name,

  category: selectedProduct.category,

  price: String(selectedProduct.price),

  quantity: String(selectedProduct.quantity),

  });

 } else {

  setEdited(empty);

 }

 }, [selectedProduct]);

 const handleChange = e => {

 const { name, value } = e.target;

 setEdited(prev => ({ ...prev, [name]: value }));

 };

 const handleSave = () => {

 updateProduct({

  id: edited.id,

  name: edited.name,

  category: edited.category,

  price: parseFloat(edited.price) || 0,

  quantity: parseInt(edited.quantity, 10) || 0,

 });

 };

 return (

 <div className="product-detail-container">

  <h2>Product Detail</h2>

  <form>

  <div>

   <label>

   Name:

   <input

    name="name"

    type="text"

    value={edited.name}

    onChange={handleChange}

   />

   </label>

  </div>

  <div>

   <label>

   Category:

   <input

    name="category"

    type="text"

    value={edited.category}

    onChange={handleChange}

   />

   </label>

  </div>

  <div>

   <label>

   Price:

   <input

    name="price"

    type="text"

    value={edited.price}

    onChange={handleChange}

   />

   </label>

  </div>

  <div>

   <label>

   Quantity:

   <input

    name="quantity"

    type="text"

    value={edited.quantity}

    onChange={handleChange}

   />

   </label>

  </div>

  <button type="button" onClick={handleSave}>

   Save

  </button>

  </form>

 </div>

 );

};

export default ProductDetail;