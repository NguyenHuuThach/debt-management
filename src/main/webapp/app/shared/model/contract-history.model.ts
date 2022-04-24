import dayjs from 'dayjs';
import { StatusContract } from 'app/shared/model/enumerations/status-contract.model';

export interface IContractHistory {
  id?: number;
  dateStart?: string;
  settlementDate?: string | null;
  totalLoanAmount?: number;
  interestPaymentPeriod?: number;
  interestRate?: number;
  customerName?: string;
  customerAddress?: string;
  customerPhoneNumber?: string;
  customerIdentityCard?: string;
  productName?: string;
  imei?: string | null;
  icloud?: string | null;
  userCreate?: string | null;
  note?: string | null;
  status?: StatusContract | null;
}

export const defaultValue: Readonly<IContractHistory> = {};
