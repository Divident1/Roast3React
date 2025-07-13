import axios from "axios";

// 1️⃣ Fetch sales data (if you want to call directly)
export const getSalesData = async () => {
  let { data } = await axios.get(`/sales.json`);
  return data;
};

// 2️⃣ Calculate Total Sales
export const calculateTotalSales = (sales) => {
  return sales.reduce((total, sale) => total + sale.saleTotal, 0);
};

// 3️⃣ Calculate Total Cash Sales (creditCard === false)
export const calculateTotalCashSale = (sales) => {
  return sales
    .filter((sale) => sale.creditCard === false)
    .reduce((total, sale) => total + sale.saleTotal, 0);
};

// 4️⃣ Calculate Total Credit Sales (creditCard === true)
export const calculateTotalCreditSale = (sales) => {
  return sales
    .filter((sale) => sale.creditCard === true)
    .reduce((total, sale) => total + sale.saleTotal, 0);
};

// 5️⃣ Calculate Buyer With Most Sales
export const calculateBuyerWithMostSale = (sales) => {
  const buyerMap = {};

  for (const sale of sales) {
    if (!buyerMap[sale.buyerName]) {
      buyerMap[sale.buyerName] = 0;
    }
    buyerMap[sale.buyerName] += sale.saleTotal;
  }

  let maxBuyer = null;
  let maxTotal = 0;

  for (const [buyer, total] of Object.entries(buyerMap)) {
    if (total > maxTotal) {
      maxBuyer = buyer;
      maxTotal = total;
    }
  }

  return {
    buyerName: maxBuyer,
    saleTotal: maxTotal
  };
};