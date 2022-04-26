// format number 1000000 to 1,234,567
export const formatNumber = number => `${number}`.replace(/\D/g, '').replace(/\B(?=(\d{3})+(?!\d))/g, ',');
