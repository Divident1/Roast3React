import axios from "axios";
import React, { useEffect, useState } from "react";
import {
  calculateTotalSales,
  calculateTotalCashSale,
  calculateTotalCreditSale,
  calculateBuyerWithMostSale
} from './Report';

function Dashboard() {
  const [data, setData] = useState([]);

  useEffect(() => {
    (async () => {
      const result = await axios.get('/sales.json');
      setData(result.data);
    })();
  }, []);

  return (
    <div className="dashboard">
      <div className="card">
        <h2>Total Sales</h2>
        <p>{calculateTotalSales(data)}</p>
      </div>
      <div className="card">
        <h2>Total Cash Sales</h2>
        <p>{calculateTotalCashSale(data)}</p>
      </div>
      <div className="card">
        <h2>Total Credit Sales</h2>
        <p>{calculateTotalCreditSale(data)}</p>
      </div>
      <div className="card">
        <h2>Buyer with Most Sales</h2>
        <p>{calculateBuyerWithMostSale(data).buyerName}</p>
        <p>{calculateBuyerWithMostSale(data).saleTotal}</p>
      </div>
    </div>
  );
}

export default Dashboard;