import dayjs from 'dayjs';
import { StatusContract } from 'app/shared/model/enumerations/status-contract.model';

export interface IContract {
  id?: number;
  dateStart?: string;
  numberInterestPayments?: number;
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
  isDeleted?: boolean | null;
}

export const defaultValue: Readonly<IContract> = {
  isDeleted: false,
};
