import dayjs from 'dayjs';

export interface IInterestPaidHistory {
  id?: number;
  contractId?: number;
  interestPaidDate?: string;
  payerName?: string | null;
  paidAmount?: number;
  note?: string | null;
}

export const defaultValue: Readonly<IInterestPaidHistory> = {};
