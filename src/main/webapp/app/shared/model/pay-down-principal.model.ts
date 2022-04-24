import dayjs from 'dayjs';

export interface IPayDownPrincipal {
  id?: number;
  contractId?: number;
  payDownPrincipalDate?: string;
  payDownPrincipalAmount?: number;
  payerName?: string | null;
  userCreate?: string | null;
  note?: string | null;
}

export const defaultValue: Readonly<IPayDownPrincipal> = {};
